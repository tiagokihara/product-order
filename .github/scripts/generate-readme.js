const fs = require("fs");
const path = require("path");
const { GoogleGenerativeAI } = require("@google/generative-ai");

const genAI = new GoogleGenerativeAI(process.env.AGENT_SECRET);

const IGNORED_FOLDERS = [
  ".git",
  "node_modules",
  "target",
  "build",
  ".idea",
  ".vscode",
  ".github"
];

const IMPORTANT_FILES = [
  "pom.xml",
  "build.gradle",
  "build.gradle.kts",
  "package.json",
  "requirements.txt",
  "pyproject.toml",
  "go.mod",
  "Cargo.toml",
  "Dockerfile",
  "docker-compose.yml",
  "docker-compose.yaml",
  "application.yml",
  "application.yaml",
  "application.properties"
];

function exists(file) {
  return fs.existsSync(file);
}

function readFileIfExists(file) {
  if (!exists(file)) return null;

  try {
    return fs.readFileSync(file, "utf8");
  } catch {
    return null;
  }
}

function buildTree(dir = ".", prefix = "") {
  const items = fs.readdirSync(dir);

  let tree = [];

  for (const item of items) {
    if (IGNORED_FOLDERS.includes(item)) continue;

    const fullPath = path.join(dir, item);

    const stats = fs.statSync(fullPath);

    tree.push(`${prefix}${item}`);

    if (stats.isDirectory()) {
      tree.push(...buildTree(fullPath, `${prefix}  `));
    }
  }

  return tree;
}

function collectImportantFiles() {
  const collected = {};

  for (const file of IMPORTANT_FILES) {
    const content = readFileIfExists(file);

    if (content) {
      collected[file] = content.substring(0, 15000);
    }
  }

  return collected;
}

function detectSourceDirectories() {
  const dirs = [];

  const possibleDirs = [
    "src/main/java",
    "src/test/java",
    "src/main/kotlin",
    "src",
    "app",
    "pages",
    "components"
  ];

  for (const dir of possibleDirs) {
    if (exists(dir)) {
      dirs.push(dir);
    }
  }

  return dirs;
}

async function main() {
  const model = genAI.getGenerativeModel({
    model: "gemini-2.5-flash"
  });

  const repoName =
    process.env.GITHUB_REPOSITORY?.split("/")[1] || "Project";

  const existingReadme = readFileIfExists("README.md") || "";

  const repositoryAnalysis = {
    repositoryName: repoName,
    structure: buildTree().slice(0, 200),
    sourceDirectories: detectSourceDirectories(),
    importantFiles: collectImportantFiles()
  };

  const prompt = `
You are analyzing a software repository.

Your job is to generate or update a README.md.

Repository analysis:
${JSON.stringify(repositoryAnalysis, null, 2)}

Existing README:
${existingReadme}

Requirements:

- If README does not exist:
  - create one
  - title must be repository name
  - include:
    - short project description
    - stack
    - how to run

- If README exists:
  - ONLY update:
    - stack section
    - how to run section
  - preserve all other sections

Important:
- Ignore CI/CD and automation tooling
- Ignore AI tooling
- Detect the REAL application stack
- Keep the README concise
- Output ONLY markdown
`;

  const result = await model.generateContent(prompt);

  let text = result.response.text();

  text = text
    .replace(/^```markdown/gm, "")
    .replace(/^```/gm, "")
    .trim();

  fs.writeFileSync("README.md", text);

  console.log("README updated successfully");
}

main();
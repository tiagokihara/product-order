const fs = require("fs");
const { GoogleGenerativeAI } = require("@google/generative-ai");

const genAI = new GoogleGenerativeAI(process.env.AGENT_SECRET);

async function main() {
  const model = genAI.getGenerativeModel({
    model: "gemini-2.5-flash",
  });

  const packageJsonExists = fs.existsSync("package.json");
  const pomExists = fs.existsSync("pom.xml");
  const gradleExists = fs.existsSync("build.gradle");

  let stack = [];

  if (packageJsonExists) {
    stack.push("Node.js");
  }

  if (pomExists) {
    stack.push("Java");
    stack.push("Maven");
  }

  if (gradleExists) {
    stack.push("Gradle");
  }

  const existingReadme = fs.existsSync("README.md")
    ? fs.readFileSync("README.md", "utf8")
    : "";

  const prompt = `
You are generating or updating a GitHub README.md.

Detected stack:
${stack.join(", ")}

Existing README:
${existingReadme}

Requirements:
- If README does not exist:
  - create one
  - include:
    - project stack
    - how to run

- If README exists:
  - ONLY update:
    - stack section
    - how to run section
  - preserve all other sections exactly

- Keep markdown clean and concise.

Output ONLY markdown.
`;

  const result = await model.generateContent(prompt);

  const text = result.response.text();

  text = text
  .replace(/^```markdown/gm, "")
  .replace(/^```/gm, "")
  .trim();

  fs.writeFileSync("README.md", text);

  console.log("README updated");
}

main();
import java.util.Date
import java.text.SimpleDateFormat

def outputDir = request.getOutputDirectory()
def artifactId = request.getArtifactId()

// .gitignore.tmpl -> .gitignore (the archetype cannot ship a dotfile directly).
def tmpl = new File(outputDir, artifactId + "/.gitignore.tmpl")
tmpl.renameTo(new File(outputDir, artifactId + "/.gitignore"))

// Resolve the target Java version. The default "auto" means "the current runtime";
// an explicit value must be between 17 (the supported floor) and the runtime version
// (you cannot target a --release newer than the JDK you generate/build with).
int runtimeJava = Runtime.version().feature()
def requested = request.getProperties().get("javaversion")
int chosen
if (requested == null || requested.trim().isEmpty() || 'auto'.equalsIgnoreCase(requested.trim())) {
    chosen = runtimeJava
} else {
    chosen = requested.trim() as int
}
if (chosen < 17) {
    throw new RuntimeException("Requested Java version ${chosen} is not supported; the minimum is 17.")
}
if (chosen > runtimeJava) {
    throw new RuntimeException("Requested Java version ${chosen} is higher than the current runtime " +
            "(Java ${runtimeJava}); cannot target a release newer than the JDK you build with.")
}
println "[homework-quickstart] Targeting Java ${chosen} (runtime Java ${runtimeJava})."

// README: fill placeholders, reporting the resolved version (not the raw "auto").
def readme = new File(outputDir, artifactId + "/README.md")
def content = readme.text
content = content.replace('${groupId}', request.getGroupId())
content = content.replace('${artifactId}', artifactId)
content = content.replace('${javaversion}', chosen.toString())
content = content.replace('${timestamp}', new SimpleDateFormat('yyyy-MM-dd HH:mm:ss').format(new Date()))
content = content.replace('${archetypeversion}', request.getArchetypeVersion())
readme.write(content)

// pom.xml: bake in the resolved java.version (drives both maven.compiler.release and
// the enforcer's minimum), and pin Checkstyle to a Java 17-20 compatible release when
// generated on an older runtime (Checkstyle 13.x requires Java 21+). The checkstyle
// regex targets the property, so it keeps working as Dependabot bumps the default.
def pom = new File(outputDir, artifactId + "/pom.xml")
def pomText = pom.text.replaceAll(/<java\.version>[^<]*<\/java\.version>/, "<java.version>${chosen}</java.version>")
if (runtimeJava < 21) {
    pomText = pomText.replaceAll(/<checkstyle\.version>[^<]+<\/checkstyle\.version>/,
            '<checkstyle.version>12.3.1</checkstyle.version>')
    println "[homework-quickstart] Java ${runtimeJava} runtime (<21): pinned Checkstyle to 12.3.1"
}
pom.write(pomText)

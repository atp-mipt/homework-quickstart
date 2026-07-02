// basedir is the cloned case dir under target/it; archetype:generate created
// the project in the "basic-project" subdirectory (its artifactId).
def pom = new File(basedir, 'basic-project/pom.xml').text
assert pom.contains('<maven.compiler.release>17</maven.compiler.release>') :
        'generated pom does not target Java 17 via maven.compiler.release'
assert pom.contains('<java.version>17</java.version>') :
        'generated pom did not bake java.version=17'
assert pom.contains('<version>[${java.version},)</version>') :
        'enforcer minimum is not wired to the chosen java.version'

def readme = new File(basedir, 'basic-project/README.md').text
assert readme.contains('Java version 17.') :
        'generated README does not report Java version 17'

return true

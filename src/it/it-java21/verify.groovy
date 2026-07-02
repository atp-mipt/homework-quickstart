def pom = new File(basedir, 'basic-project/pom.xml').text
assert pom.contains('<maven.compiler.release>21</maven.compiler.release>') :
        'generated pom does not target Java 21 via maven.compiler.release'
assert pom.contains('<java.version>21</java.version>') :
        'generated pom did not bake java.version=21'
assert pom.contains('<version>[${java.version},)</version>') :
        'enforcer minimum is not wired to the chosen java.version'

def readme = new File(basedir, 'basic-project/README.md').text
assert readme.contains('Java version 21.') :
        'generated README does not report Java version 21'

return true

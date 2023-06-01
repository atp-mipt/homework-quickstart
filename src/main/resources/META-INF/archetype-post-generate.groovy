import java.util.Date
import java.text.SimpleDateFormat


def file = new File( request.getOutputDirectory(), request.getArtifactId()+"/.gitignore.tmpl" )
def gitIgnorefile = new File( request.getOutputDirectory(), request.getArtifactId()+"/.gitignore" )
file.renameTo(gitIgnorefile)

def readme = new File( request.getOutputDirectory(), request.getArtifactId()+"/README.md"  )

def content = readme.text

content = content.replace('${groupId}', request.getGroupId())
content = content.replace('${artifactId}', request.getArtifactId())
content = content.replace('${javaversion}', request.getProperties().get("javaversion"))
def formatter = new SimpleDateFormat('yyyy-MM-dd HH:mm:ss')
content = content.replace('${timestamp}', formatter.format(new Date()))

readme.write(content)

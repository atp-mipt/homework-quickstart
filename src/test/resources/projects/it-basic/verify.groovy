// The export:export goal (export-maven-plugin) bundles the generated project
// into target/export.zip. For an archetype IT, basedir is the test project
// directory; the generated project lives under project/<artifactId>.
def zip = new File(basedir, 'project/basic-project/target/export.zip')
assert zip.isFile() : "export.zip was not created at ${zip}"
assert zip.length() > 0 : "export.zip is empty"

// Verify the ZIP local-file-header magic number (PK\x03\x04, 0x504B0304),
// which is present only when the archive actually contains entries.
def magic = new byte[4]
zip.withInputStream { ins ->
    assert ins.read(magic) == 4 : "export.zip is shorter than 4 bytes"
}
def expected = [0x50, 0x4B, 0x03, 0x04] as byte[]
assert java.util.Arrays.equals(magic, expected) :
        "export.zip does not start with the ZIP magic number 0x504B0304; got " +
        magic.collect { String.format('0x%02X', it & 0xFF) }.join(' ')
return true

public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    /**
     * This is a constructor method that intializes FileData
     * and assigns values to instance variables.
     * 
     * @param name
     * @param directory
     * @param modifiedDate
     */
    public FileData(String name, String directory, String modifiedDate) {
        this.name = name;
        this.dir = directory;
        this.lastModifiedDate = modifiedDate;

    }

    /**
     * This is a method that formats instance variables into a string 
     * and returns that string.
     * 
     * @return a formatted string
     */
    public String toString() {
        return "{Name: " + this.name + ", Directory: "+
                            this.dir + ", Modified Date: "
                            + this.lastModifiedDate+ "}";

    }
}
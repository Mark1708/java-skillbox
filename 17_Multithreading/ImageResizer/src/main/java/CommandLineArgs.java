import com.beust.jcommander.Parameter;

class CommandLineArgs {
    @Parameter(
            names = {"--src", "-s"},
            description = "Path to source image/directory",
            required = true
    )
    private String srcPath;
    @Parameter(
            names = {"--dst", "-d"},
            description = "Path to destination directory (by default image(s) will be saved in src directory)",
            required = false
    )
    private String dstPath = "";
    @Parameter(
            names = {"--size", "-S"},
            description = "New image size (by default the image will be compressed to size 300px)",
            required = false
    )
    private int newSize = 300;
    @Parameter(
            names = {"--mode", "-m"},
            description = "Mode of resizing image\n1 - resize by nearest neighbor algorithm\n2 - resize by imgscalr\n3 - resize by Graphics2D with RenderingHints",
            required = false
    )
    private int mode = 3;

    public String getSrcPath() {
        return srcPath;
    }

    public String getDstPath() {
        return dstPath;
    }

    public int getNewSize() {
        return newSize;
    }

    public int getMode() {
        return mode;
    }
}


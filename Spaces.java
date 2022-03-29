/**
* Name: Lucy Fekade
* Pennkey: lucyfe
* Execution: N/A
*
* Description: This class creates the spaces or tiles that go on to the board
**/
public class Spaces {

    private double xCoord;
    private double yCoord;
    private int value;
    public Spaces(double xCoord, double yCoord, int value) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.value = value;
    }
    /**
    * Inputs: N/A
    * Outputs: N/A
    * Description: this method draws the spaces (tiles) where each value has
    * its own color
    */
    public void draw() {
        if (value == 2) {
            PennDraw.setPenColor(213, 204, 237);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "2");
        } else if (value == 4) {
            PennDraw.setPenColor(205, 191, 245);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "4");
        } else if (value == 8) {
            PennDraw.setPenColor(192, 173, 247);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "8");
        } else if (value == 16) {
            PennDraw.setPenColor(166, 141, 242);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "16");
        } else if (value == 32) {
            PennDraw.setPenColor(149, 118, 245);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "32");
        } else if (value == 64) {
            PennDraw.setPenColor(134, 99, 242);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "64");
        } else if (value == 128) {
            PennDraw.setPenColor(107, 66, 235);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "128");
        } else if (value == 256) {
            PennDraw.setPenColor(79, 40, 201);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122, 0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "256");
        } else if (value == 512) {
            PennDraw.setPenColor(62, 26, 173);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "512");
        } else if (value == 1024) {
            PennDraw.setPenColor(45, 18, 128);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "1024");
        } else if (value == 2048) {
            PennDraw.setPenColor(27, 6, 94);
            PennDraw.filledSquare(xCoord / 4 + 0.122, yCoord / 4 + 0.122,
                                                                         0.115);
            PennDraw.setPenColor(252, 252, 252);
            PennDraw.setFontSize(40);
            PennDraw.text(xCoord / 4 + 0.122, yCoord / 4 + 0.122, "2048");
        }

    }


    /**
    * Inputs: N/A
    * Outputs: an integer value
    * Description: this method returns the current value held at a space
    */
    public int getValue() {
        return this.value;
    }

}

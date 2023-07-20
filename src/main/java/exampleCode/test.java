package exampleCode;

import jGame.core.Size;
import jGame.output.Frame;

public class test {
    public static void main(String[] args) {
        Frame frame = new Frame.Builder()
                .setSize(new Size(1000, 800))
                .build();
    }
}

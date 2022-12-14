interface Model3D {
    public int calcPixel(int x, int y);
}

class SimpleModel implements Model3D {
    @Override
    public int calcPixel(int x, int y) {
        int red = (x*x - 2*x + 15) % 256;
        int grn = (x*x*x - 5*x*x + 3*x) % 256;
        int blu = (5*x*x - 5*x -31) % 256;
        return red*0x10000 + grn*0x100 + blu;
    }
}

public class Render {
    public Render(Model3D model) {this.model = model;}
    public int[][] createImage(int width, int height) {
        int[][] image = new int[width][height];
        for(int x=0; x<width; ++x) {
            for(int y=0; y<height; ++y) {
                image[x][y] = model.calcPixel(x, y);
                if(image[x][y] == 0) ++black;
            }
        }
        return image;
    }
    public int numberOfBlackPixels() {return black;}
    private Model3D model;
    private int black = 0;
}

class ThreadedRender {
    public ThreadedRender(Model3D model) {this.model = model;}
    public int[][] createImage(int width, int height) {
        int[][] image = new int[width][height];
        Thread[] threads = new Thread[width]; // 1
        for(int x=0; x<width; ++x) {
            final int fx = x; // ½
            threads[x] = new Thread(() -> { // 1
                for(int y=0; y<height; ++y) { // ½
                    image[fx][y] = model.calcPixel(fx, y); // ½
                    if(image[fx][y] == 0) {
                        synchronized(mutex) {++black;} // 1 = ½ synchronized ½ mutex
                    }
                }
            });
            threads[x].start(); // 1
        }
        for(Thread t : threads) { // 1
            try {
                t.join();
            } catch(Exception e) {
            }
        }
        return image;
    }
    public int numberOfBlackPixels() {return black;}
    private Model3D model;
    private int black = 0;
    private static Object mutex; // 1 Object or synchronized method
}


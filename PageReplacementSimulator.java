import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PageReplacementSimulator {

    private static final List<Integer> PAGE_REFERENCE_STRING = Arrays.asList(
            7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1
    );
    private static final int FRAME_COUNT = 3;

    public static int simulateFIFO(List<Integer> referenceString, int frameCount) {
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        for (int page : referenceString) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.addLast(page);
                } else {
                    frames.removeFirst();
                    frames.addLast(page);
                }
            }
        }
        return pageFaults;
    }

    public static int simulateLRU(List<Integer> referenceString, int frameCount) {
        LinkedList<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        for (int page : referenceString) {
            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.addLast(page);
                } else {
                    frames.removeFirst();
                    frames.addLast(page);
                }
            } else {
                frames.remove((Integer) page);
                frames.addLast(page);
            }
        }
        return pageFaults;
    }

    public static int simulateOptimal(List<Integer> referenceString, int frameCount) {
        List<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        for (int i = 0; i < referenceString.size(); i++) {
            int page = referenceString.get(i);

            if (!frames.contains(page)) {
                pageFaults++;
                if (frames.size() < frameCount) {
                    frames.add(page);
                } else {
                    int farthestUse = -1;
                    int pageToReplace = -1;

                    for (int framePage : frames) {
                        int nextUse = -1;
                        for (int j = i + 1; j < referenceString.size(); j++) {
                            if (referenceString.get(j).equals(framePage)) {
                                nextUse = j;
                                break;
                            }
                        }

                        if (nextUse == -1) {
                            pageToReplace = framePage;
                            break;
                        } else if (nextUse > farthestUse) {
                            farthestUse = nextUse;
                            pageToReplace = framePage;
                        }
                    }

                    frames.remove((Integer) pageToReplace);
                    frames.add(page);
                }
            }
        }
        return pageFaults;
    }

    public static int simulateClock(List<Integer> referenceString, int frameCount) {
        class Frame {
            int page = -1;
            int useBit = 0;
        }

        Frame[] frames = new Frame[frameCount];
        for (int i = 0; i < frameCount; i++) {
            frames[i] = new Frame();
        }

        int pageFaults = 0;
        int pointer = 0;

        for (int page : referenceString) {
            boolean found = false;
            for (Frame frame : frames) {
                if (frame.page == page) {
                    frame.useBit = 1;
                    found = true;
                    break;
                }
            }

            if (!found) {
                pageFaults++;
                while (true) {
                    if (frames[pointer].page == -1) {
                        frames[pointer].page = page;
                        frames[pointer].useBit = 1;
                        pointer = (pointer + 1) % frameCount;
                        break;
                    } else if (frames[pointer].useBit == 0) {
                        frames[pointer].page = page;
                        frames[pointer].useBit = 1;
                        pointer = (pointer + 1) % frameCount;
                        break;
                    } else {
                        frames[pointer].useBit = 0;
                        pointer = (pointer + 1) % frameCount;
                    }
                }
            }
        }
        return pageFaults;
    }

    public static void main(String[] args) {
        System.out.println("--- Simulador de Algoritmos de Substituição de Páginas ---");
        System.out.println("String de Referência: " + PAGE_REFERENCE_STRING);
        System.out.println("Número de Frames: " + FRAME_COUNT);

        int faultsFIFO = simulateFIFO(PAGE_REFERENCE_STRING, FRAME_COUNT);
        int faultsLRU = simulateLRU(PAGE_REFERENCE_STRING, FRAME_COUNT);
        int faultsOptimal = simulateOptimal(PAGE_REFERENCE_STRING, FRAME_COUNT);
        int faultsClock = simulateClock(PAGE_REFERENCE_STRING, FRAME_COUNT);

        System.out.println("\nFaltas de Página:");
        System.out.println("- FIFO: " + faultsFIFO);
        System.out.println("- LRU: " + faultsLRU);
        System.out.println("- Ótimo: " + faultsOptimal);
        System.out.println("- Relógio: " + faultsClock);
    }
}

package jay.util;

import jay.util.math.geom.Line2d;
import jay.util.math.geom.Translation2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class Board2d<Tile> implements  Iterable<Tile> {

    private final int width, height;
    private final Tile tiles[];

    @SuppressWarnings("unchecked")
    public Board2d(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = (Tile[]) new Object[width * height];
    }

    public void set(int index, Tile tile) {
        tiles[index] = tile;
    }

    public void set(int x, int y, Tile tile) {
        int index = toIndex(x, y);
        set(index, tile);
    }

    public void set(Translation2d translation, Tile tile) {
        set((int)translation.getX(), (int)translation.getY(), tile);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    private int toIndex(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            throw new IndexOutOfBoundsException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return y * width + x;
    }

    // y * width + x = index

    public Translation2d getTranslation(int index) {
        int x = index % width;
        int y = index / width - 1;
        return new Translation2d(x, y);
    }

    public Tile get(int index) {
        if (index < 0 || index >= tiles.length) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds: " + index
            );
        }
        return tiles[index];
    }

    public void foreach(Consumer<Tile> consumer) {
        for(Tile tile : this) {
            consumer.accept(tile);
        }
    }

    public Tile get(Translation2d translation) {
        return get((int)translation.getX(), (int)translation.getY());
    }

    public Optional<List<CardinalDirection>> blockedByBoard(Translation2d translation) {
        ArrayList<CardinalDirection> directionsBlocked = new ArrayList<>();
        if(translation.getX() == 0) directionsBlocked.add(CardinalDirection.EAST);
        if(translation.getX() == getWidth() - 1) directionsBlocked.add(CardinalDirection.WEST);
        if(translation.getY() == getHeight() - 1) directionsBlocked.add(CardinalDirection.NORTH);
        if(translation.getY() == 0) directionsBlocked.add(CardinalDirection.SOUTH);
        return directionsBlocked.isEmpty() ? Optional.empty() : Optional.of(directionsBlocked);
    }

    public Translation2d getCenter() {
        return new Translation2d(
                width / 2.0,
                height / 2.0
        );
    }

    public Translation2d getCenterRelativeToIndices() {
        return new Translation2d(
                (width - 1.0) / 2.0,
                (height - 1.0) / 2.0
        );
    }

    public Tile get(int x, int y) {
        return get(toIndex(x, y));
    }

    @Override
    public Iterator<Tile> iterator() {
        return new Board2dIterator<>(this);
    }

    public static class Board2dIterator<T> implements Iterator<T> {

        private int m_currentIndex = 0;
        private final Board2d<T> m_board;

        public Board2dIterator(Board2d<T> board) {
            m_currentIndex = 0;
            this.m_board = board;
        }

        @Override
        public boolean hasNext() {
            return m_currentIndex < m_board.getArea();
        }

        @Override
        public T next() {
            return m_board.get(m_currentIndex);
        }
    }

}



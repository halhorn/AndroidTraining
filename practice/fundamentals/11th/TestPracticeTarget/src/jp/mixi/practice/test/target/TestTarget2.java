package jp.mixi.practice.test.target;

/**
 * TODO: TestPractice2 の各テストケースをパスするメソッドを書く
 */
public class TestTarget2 {
    public boolean isValidLength(String string) {
        return string.length() > 0 && string.length() <= 10;
    }

    public String formatTextCount(int count, int max) {
        return String.format("%d / %d", count, max);
    }
}
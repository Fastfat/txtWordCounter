import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String path = "";
        String readingResult;
        String[] result = null;
        String[] resultValues = null;
        String[] finalValues = null;
        ArrayList<String[]> valueList = new ArrayList<>();
        try {
            File dir = new File("resources");
            for(File item : Objects.requireNonNull(dir.listFiles())) {
                path = dir + "\\" + item.getName();
                readingResult = readFileContentsOrNull(path).toLowerCase(Locale.ROOT);
                result = readingResult.split("\\n");
                for (int i = 1; i < result.length; i++) {
                    resultValues = result[i].split(" ");
                    finalValues = Arrays.copyOfRange(resultValues, 0, resultValues.length-1);
                    char[] word = finalValues[0].toCharArray();
                    String firstWord = "";
                    for (int j = 0; j < word.length; j++) {
                        if (word[j] == '\t') {
                            while (word[j] == ' ') {
                                j++;
                            }
                            firstWord = String.valueOf(word).substring(j+1);
                        }
                    }
                    char[] wordEnd = null;
                    for (int ii = 0; ii < finalValues.length; ii++) {
                        wordEnd = finalValues[ii].toCharArray();
                        int end = wordEnd.length;
                        if (end > 3) {
                            if (wordEnd[end - 3] == 'i' && wordEnd[end - 2] == 'n' && wordEnd[end-1] == 'g') {
                                finalValues[ii] = String.valueOf(wordEnd).substring(0, end - 3);
                            }
                            if (wordEnd[end - 2] == 'e' && wordEnd[end-1] == 'd') {
                                finalValues[ii] = String.valueOf(wordEnd).substring(0, end - 2);
                            }
                            if (wordEnd[end - 1] == 's') {
                                finalValues[ii] = String.valueOf(wordEnd).substring(0, end - 1);
                            }
                            finalValues[ii] = checkIrregularVerb(finalValues[ii]);
                        }
                    }
                    finalValues[0] = firstWord;
                    valueList.add(finalValues);
                }
            }
            HashMap<String, Integer> mainResult = new HashMap<>();
            for (String[] mas : valueList) {
                for (String word : mas) {
                    if (!mainResult.containsKey(word)) {
                        int count = 0;
                        for (String[] mas2 : valueList) {
                            for (String word2 : mas2) {
                                if (word.equals(word2)) {
                                    if (mainResult.containsKey(word)) {
                                        count = mainResult.get(word);
                                        mainResult.put(word, count + 1);
                                    } else {
                                        mainResult.put(word, 2);
                                    }
                                } else if (word.contains(word2) && (word.length() - word2.length() == 1)) {
                                    word2 = word;
                                    if (mainResult.containsKey(word)) {
                                        count = mainResult.get(word);
                                        mainResult.put(word, count + 1);
                                    } else {
                                        mainResult.put(word, 2);
                                    }
                                } else if (word2.contains(word) && (word2.length() - word.length() == 1)) {
                                    word = word2;
                                    if (mainResult.containsKey(word2)) {
                                        count = mainResult.get(word2);
                                        mainResult.put(word2, count + 1);
                                    } else {
                                        mainResult.put(word2, 2);
                                    }
                                }
                            }
                            count = 0;
                        }
                        count = 0;
                    }
                    if (!mainResult.containsKey(word)) {
                        mainResult.put(word, 1);
                    }
                }
            }
            System.out.println(mainResult);
        } catch (NullPointerException e) {
            System.out.println("!!!");
        }

    }

    private static String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private static String checkIrregularVerb(String word) {
        if (word.equals("was") || word.equals("were") || word.equals("been")) {
            word = "be";
            return word;
        }
        if (word.equals("became")) {
            word = "become";
            return word;
        }
        if (word.equals("began") || (word.equals("begun"))) {
            word = "begin";
            return word;
        }
        if (word.equals("broke") || (word.equals("broken"))) {
            word = "break";
            return word;
        }
        if (word.equals("brought")) {
            word = "bring";
            return word;
        }
        if (word.equals("built")) {
            word = "build";
            return word;
        }
        if (word.equals("bought")) {
            word = "buy";
            return word;
        }
        if (word.equals("chose") || (word.equals("chosen"))) {
            word = "choose";
            return word;
        }
        if (word.equals("came")) {
            word = "come";
            return word;
        }
        if (word.equals("did") || (word.equals("done"))) {
            word = "do";
            return word;
        }
        if (word.equals("drove") || (word.equals("driven"))) {
            word = "drive";
            return word;
        }
        if (word.equals("fell") || (word.equals("fallen"))) {
            word = "fall";
            return word;
        }
        if (word.equals("felt")) {
            word = "feel";
            return word;
        }
        if (word.equals("found")) {
            word = "find";
            return word;
        }
        if (word.equals("got")) {
            word = "get";
            return word;
        }
        if (word.equals("gave") || (word.equals("given"))) {
            word = "give";
            return word;
        }
        if (word.equals("went") || (word.equals("gone"))) {
            word = "go";
            return word;
        }
        if (word.equals("grew") || (word.equals("grown"))) {
            word = "grow";
            return word;
        }
        if (word.equals("had")) {
            word = "have";
            return word;
        }
        if (word.equals("heard")) {
            word = "hear";
            return word;
        }
        if (word.equals("held")) {
            word = "hold";
            return word;
        }
        if (word.equals("kept")) {
            word = "keep";
            return word;
        }
        if (word.equals("knew") || (word.equals("known"))) {
            word = "know";
            return word;
        }
        if (word.equals("led")) {
            word = "lead";
            return word;
        }
        if (word.equals("left")) {
            word = "leave";
            return word;
        }
        if (word.equals("lay") || (word.equals("lain"))) {
            word = "lie";
            return word;
        }
        if (word.equals("lost")) {
            word = "lose";
            return word;
        }
        if (word.equals("made")) {
            word = "make";
            return word;
        }
        if (word.equals("meant")) {
            word = "mean";
            return word;
        }
        if (word.equals("met")) {
            word = "meet";
            return word;
        }
        if (word.equals("paid")) {
            word = "pay";
            return word;
        }
        if (word.equals("ran")) {
            word = "run";
            return word;
        }
        if (word.equals("said")) {
            word = "say";
            return word;
        }
        if (word.equals("saw") || (word.equals("seen"))) {
            word = "see";
            return word;
        }
        if (word.equals("sent")) {
            word = "send";
            return word;
        }
        if (word.equals("showed") || (word.equals("shown"))) {
            word = "show";
            return word;
        }
        if (word.equals("sat")) {
            word = "sit";
            return word;
        }
        if (word.equals("spoke") || (word.equals("spoken"))) {
            word = "speak";
            return word;
        }
        if (word.equals("spent")) {
            word = "spend";
            return word;
        }
        if (word.equals("stood")) {
            word = "stand";
            return word;
        }
        if (word.equals("took") || (word.equals("taken"))) {
            word = "take";
            return word;
        }
        if (word.equals("told")) {
            word = "tell";
            return word;
        }
        if (word.equals("thought")) {
            word = "think";
            return word;
        }
        if (word.equals("understood")) {
            word = "understand";
            return word;
        }
        if (word.equals("wore") || (word.equals("worn"))) {
            word = "wear";
            return word;
        }
        if (word.equals("wrote") || (word.equals("written"))) {
            word = "write";
            return word;
        }
        return word;
    }
}

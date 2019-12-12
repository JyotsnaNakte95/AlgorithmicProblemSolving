import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import javax.lang.model.type.ArrayType;

// Author : Jyotsna Nakte
class WordLadderII {

    Map<String, List<String>> parentToChild = new HashMap<>();

    public List getNextList(String currentWord, Set<String> wordList) {
        char[] stringBuilderForCurrentWord = currentWord.toCharArray();
        List<String> currentWordList = new ArrayList<>();
        for(int i = 0; i < currentWord.length(); i++) {
            char previousChar = stringBuilderForCurrentWord[i];
            for(char currentChar = 'a'; currentChar <= 'z'; currentChar++) {
                if(stringBuilderForCurrentWord[i] == currentChar) continue;
                stringBuilderForCurrentWord[i] = currentChar;
                String newWord = String.valueOf(stringBuilderForCurrentWord);
                if(!newWord.equals(currentWord) && wordList.contains(newWord)) {
                    currentWordList.add(newWord);
                }
            }
            stringBuilderForCurrentWord[i] = previousChar;
        }
        return currentWordList;
    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> finalResults = new ArrayList<>();
        if(!wordList.contains(endWord)) return finalResults;
        int steps = 0;
        LinkedList<String> queue = new LinkedList<>();
        Map<String, List<String>> parentMap = new HashMap<>();
        Set<String> wordSetList = new HashSet<>(wordList);
        queue.add(beginWord);
        parentMap.put(beginWord, new ArrayList<>());
        parentMap.get(beginWord).add("END");
        boolean found = false;
        while(!found) {
            steps++;
            int n = queue.size();
            for(int i = 0; i < n; i++) {
                String currentWord = queue.pop();
                if(currentWord.equals(endWord)) {
                    found = true;
                }
                List<String> nextChild = getNextList(currentWord, wordSetList);
                for(String child : nextChild) {
                    if(!parentMap.containsKey(child)) {
                        queue.add(child);
                        parentMap.put(child, new ArrayList<>());
                    }
                    parentMap.get(child).add(currentWord);
                }
            }
            if(queue.isEmpty()) break;
        }
        if(!found) return  finalResults;
        Map<String, Boolean> tempVisited = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(endWord, true);
        List<String> result = new ArrayList<>();
        result.add(endWord);
        finalResults.add(result);
        tempVisited.put(endWord, true);
        for(int i = 0; i < steps - 1; i++) {
            List<List<String>> tempResult = new ArrayList<>();
            for(List<String> childList : finalResults) {
                if(childList.get(childList.size() - 1).equals(beginWord)){
                    tempResult = finalResults;
                    break;
                }
                for (String parent : parentMap.get(childList.get(childList.size() - 1))) {
                    if(!tempVisited.containsKey(parent)) {
                        List<String> path = new ArrayList<>(childList);
                        path.add(parent);
                        tempResult.add(path);
                        visited.put(parent, true);
                    }
                }
            }
            finalResults = tempResult;
            tempVisited = new HashMap<>(visited);
        }
        List<List<String>> results = new ArrayList<>();
        for(List<String> resul : finalResults) {
            if(resul.contains(beginWord)) {
                Collections.reverse(resul);
                results.add(resul);
            }
        }
        return results;
    }
}
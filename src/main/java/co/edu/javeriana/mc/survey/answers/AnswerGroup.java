package co.edu.javeriana.mc.survey.answers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javassist.NotFoundException;

@SuppressWarnings("unchecked")
public class AnswerGroup extends LinkedHashMap<String, Object> {

    class InvalidPathException extends RuntimeException {

        public InvalidPathException(String message) {
            super(message);
        }

    }

    private Map<String, Object> allAnswers = new LinkedHashMap<>();

    public void addAnswer(String path, Object answer) {
        allAnswers.put(path, answer);
        String[] pathElements = path.split("\\.", 2);
        Object child = getOrDefault(pathElements[0], new AnswerGroup());
        if (pathElements.length > 1) {
            if (child instanceof AnswerGroup) {
                put(pathElements[0], child);
                AnswerGroup nextAnswerGroup = (AnswerGroup) child;
                nextAnswerGroup.addAnswer(pathElements[1], answer);
            } else {
                throw new InvalidPathException("Intermediate element " + pathElements[0] + " is not an AnswerGroup");
            }
        } else {
            put(pathElements[0], answer);
        }
    }

    public void addAnswersByColumnName(Map<String, Object> answersByColumnName) {
        for (Entry<String, Object> entry : answersByColumnName.entrySet()) {
            addAnswer(entry.getKey(), entry.getValue());
        }
    }

    public <T> T getAnswer(String path) throws NotFoundException {
        String[] pathElements = path.split("\\.");
        AnswerGroup current = this;
        int i = 0;
        for (; i < pathElements.length - 1; i++) {
            Object next = current.get(pathElements[i]);
            if (next instanceof AnswerGroup) {
                current = (AnswerGroup) next;
            } else {
                throw new InvalidPathException("Answer with path " + path + "does not exist");
            }

        }
        return (T) current.get(pathElements[i]);

    }

    public Map<String, Object> getAllAnswers() {
        return allAnswers;
    }

    public <T> List<T> getAllAnswerValuesByType(Class<T> c) {
        return (List<T>) allAnswers.values().stream().filter(c::isInstance).collect(Collectors.toList());
    }

    // SPEL Functions

    public static float sum(AnswerGroup answerGroup) {
        return (float) answerGroup.getAllAnswerValuesByType(Number.class).stream().reduce(0,
                (a, b) -> a.floatValue() + b.floatValue());
    }

    public static float avg(AnswerGroup answerGroup) {
        return sum(answerGroup) / answerGroup.getAllAnswerValuesByType(Number.class).size();
    }

    public static float max(AnswerGroup answerGroup) {
        return answerGroup.getAllAnswerValuesByType(Number.class).stream().max((a, b) -> a.intValue() - b.intValue())
                .get().floatValue();
    }

    public static float min(AnswerGroup answerGroup) {
        return answerGroup.getAllAnswerValuesByType(Number.class).stream().min((a, b) -> a.intValue() - b.intValue())
                .get().floatValue();
    }

}

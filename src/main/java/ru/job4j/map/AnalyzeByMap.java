package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {

    public static double averageScore(List<Pupil> pupils) {
        double sum = 0;
        double countSub = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
                countSub++;
            }
        }
        return sum / countSub;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        double sum = 0;
        double countSub = 0;
        List<Label> labelList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.score();
                countSub++;
            }
            double aver = sum / countSub;
            labelList.add(new Label(pupil.name(), aver));
            sum = 0;
            countSub = 0;
        }
        return labelList;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> subjectAverageRate = sumScoreBySubject(pupils);
        List<Label> labelList = new ArrayList<>();
        for (String subject : subjectAverageRate.keySet()) {
            double aver = subjectAverageRate.get(subject) / pupils.size();
            Label label = new Label(subject, aver);
            labelList.add(label);
        }
        return labelList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labelList = new ArrayList<>();
        double sumScore = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sumScore += subject.score();
            }
            labelList.add(new Label(pupil.name(), sumScore));
            sumScore = 0;
        }
        labelList.sort(Comparator.naturalOrder());
        Label bestPupil = labelList.get(labelList.size() - 1);
        return bestPupil;
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> subjectAverageRate = sumScoreBySubject(pupils);
        List<Label> labelList = new ArrayList<>();
        for (String subject : subjectAverageRate.keySet()) {
            labelList.add(new Label(subject, subjectAverageRate.get(subject)));
        }
        labelList.sort(Comparator.naturalOrder());
        Label bestSubject = labelList.get(labelList.size() - 1);
        return bestSubject;
    }

    private static Map<String, Integer> sumScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> subjectAverageRate = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String subjectName = subject.name();
                int currentScore = subject.score();
                int lastScore = 0;
                if (subjectAverageRate.containsKey(subjectName)) {
                    lastScore = subjectAverageRate.get(subjectName);
                }
                int sum = lastScore + currentScore;
                subjectAverageRate.put(subjectName, sum);
            }
        }
        return subjectAverageRate;
    }
}
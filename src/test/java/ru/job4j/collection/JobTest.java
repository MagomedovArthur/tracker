package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

class JobTest {

    @Test
    void whenAscByName() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobAscByName();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(0));
    }

    @Test
    void whenAscByPriority() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobAscByPriority();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(-1));
    }

    @Test
    void whenDescByName() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobDescByName();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(0));
    }

    @Test
    void whenDescByPriority() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobDescByPriority();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(1));
    }

    @Test
    void byDescNameAndDescPriority() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, greaterThan(0));
    }

    @Test
    void byAscNameAndAscPriority() {
        Job job1 = new Job("Fix bug", 0);
        Job job2 = new Job("Fix bug", 4);
        Comparator<Job> comparator = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, lessThan(0));
    }
}
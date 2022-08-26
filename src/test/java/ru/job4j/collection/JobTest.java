package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

class JobTest {

    Job job1 = new Job("Fix bug", 0);
    Job job2 = new Job("Fix bug", 4);
    Comparator<Job> comparator;

    @Test
    void whenAscByName() {
        comparator = new JobAscByName();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(0));
    }

    @Test
    void whenAscByPriority() {
        comparator = new JobAscByPriority();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(-1));
    }

    @Test
    void whenDescByName() {
        comparator = new JobDescByName();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(0));
    }

    @Test
    void whenDescByPriority() {
        comparator = new JobDescByPriority();
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, is(1));
    }

    @Test
    void byDescNameAndDescPriority() {
        comparator = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, greaterThan(0));
    }

    @Test
    void byAscNameAndAscPriority() {
        comparator = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = comparator.compare(job1, job2);
        assertThat(rsl, lessThan(0));
    }
}
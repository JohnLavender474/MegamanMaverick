package com.game.utils.objects;

import com.game.animations.TimeMarkedRunnable;
import com.game.updatables.Updatable;
import com.game.utils.interfaces.Resettable;
import lombok.Getter;

import java.util.*;

/**
 * Timer that ticks up from 0 to {@link #duration}. Can be injected with {@link TimeMarkedRunnable} instances.
 */
@Getter
public class Timer implements Updatable, Resettable {

    private final Set<TimeMarkedRunnable> timeMarkedRunnables = new TreeSet<>();
    private final Queue<TimeMarkedRunnable> timeMarkedRunnableQueue = new PriorityQueue<>();
    private float time;
    private float duration;
    private boolean justFinished;

    /**
     * Instantiates a new Time ticker.
     */
    public Timer() {
        this(1f);
    }

    /**
     * Instantiates a new Time ticker.
     *
     * @param duration            the duration
     * @param timeMarkedRunnables the time marked runnables
     */
    public Timer(float duration, TimeMarkedRunnable... timeMarkedRunnables) {
        this(duration, Arrays.asList(timeMarkedRunnables));
    }

    /**
     * Instantiates a new Time ticker.
     *
     * @param duration            the duration
     * @param timeMarkedRunnables the time marked runnables
     */
    public Timer(float duration, Collection<TimeMarkedRunnable> timeMarkedRunnables) {
        setDuration(duration);
        reset();
        timeMarkedRunnables.forEach(timeMarkedRunnable -> {
            if (timeMarkedRunnable.time() < 0f || timeMarkedRunnable.time() > duration) {
                throw new IllegalStateException();
            }
        });
        this.timeMarkedRunnables.addAll(timeMarkedRunnables);
    }

    /**
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(float duration) {
        if (duration < 0f) {
            throw new IllegalStateException();
        }
        this.duration = duration;
    }

    /**
     * Gets ratio between duration (max time) and current time.
     *
     * @return the ratio
     */
    public float getRatio() {
        return duration > 0f ? Math.min(time / duration, 1f) : 0f;
    }

    /**
     * Returns if {@link #time} is equal to zero.
     *
     * @return true if time = 0
     */
    public boolean isAtBeginning() {
        return time == 0f;
    }

    /**
     * Returns if {@link #time} is greater than or equal to {@link #duration}.
     *
     * @return true if time >= duration
     */
    public boolean isFinished() {
        return time >= duration;
    }

    /**
     * Is the provided time greater than or equal to this timer's time.
     *
     * @param time the time
     * @return if the provided time is greater than or equal to this timer's time.
     */
    public boolean isTimeGreaterThanOrEqualTo(float time) {
        return this.time <= time;
    }

    /**
     * Returns if {@link #time} is greater than or equal to {@link #duration} and was not previously.
     *
     * @return true is time just became greater than or equal to duration
     */
    public boolean isJustFinished() {
        return justFinished;
    }

    /**
     * Sets to end.
     */
    public void setToEnd() {
        time = duration;
    }

    @Override
    public void update(float delta) {
        boolean finishedBefore = isFinished();
        time = Math.min(duration, time + delta);
        while (!timeMarkedRunnableQueue.isEmpty() && timeMarkedRunnableQueue.peek().time() <= time) {
            TimeMarkedRunnable timeMarkedRunnable = timeMarkedRunnableQueue.poll();
            if (timeMarkedRunnable == null || timeMarkedRunnable.runnable() == null) {
                continue;
            }
            timeMarkedRunnable.runnable().run();
        }
        justFinished = !finishedBefore && isFinished();
    }

    @Override
    public void reset() {
        time = 0f;
        timeMarkedRunnableQueue.clear();
        timeMarkedRunnableQueue.addAll(timeMarkedRunnables);
    }

}
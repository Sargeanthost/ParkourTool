package girraiffe;

import java.util.ArrayList;
import java.util.List;

public class TierHelper {
    private float position = 0.0f;
    private float nextPosition = 0.0f;
    private float minimumBottomBlipHeight = 0.0f;

    public boolean isBlipPossible(
            float blipTopHeight, float blipBottomHeight, float position, float nextPosition) {
        if (blipBottomHeight >= blipTopHeight || position < blipTopHeight) {
            return false;
        }
        if (position > blipTopHeight && nextPosition < blipBottomHeight) {
            return true;
        }
        System.out.println("Unexpected blip condition");
        return true;
    }

    public void calculateOffsets(float blipBottomHeight, float startingHeight) {
        // https://www.mcpk.wiki/wiki/Tiers
        final double MM_CUTOFF_1_8 = 0.005;
        int ticks = 1;
        float momentum = 0.42f;
        float nextOffset = momentum;
        float offset = 0.0f;
        float position = startingHeight;
        float positionNextTick = position; // not good for first tick

        while (true) {
            if (ticks < 7 || !(positionNextTick < blipBottomHeight)) {
                ++ticks;
                offset += momentum;
                momentum -= 0.08f;
                momentum *= 0.98f;

                if (Math.abs(momentum) >= MM_CUTOFF_1_8) {
                    nextOffset += momentum;
                } else {
                    momentum = 0;
                }
                position = startingHeight + offset;
                positionNextTick = startingHeight + nextOffset;
                continue;
            }
            break;
        }
        setMinimumBottomBlipHeight(positionNextTick);
        setPositions(position, positionNextTick);
    }

    public List<Float> offsetList(float startingHeight, boolean hasJumped) {
        final double MM_CUTOFF_1_8 = 0.005;
        float momentum = hasJumped ? 0.42f : 0.0f;
        float nextOffset = momentum;
        float offset = 0.0f;
        int ticks = 1;
        List<Float> newOffsetArray = new ArrayList<>();

        while (true) {
            if (ticks < 12 || startingHeight > Math.abs(nextOffset)) {
                ++ticks;
                offset += momentum;
                momentum -= 0.08f;
                momentum *= 0.98f;

                if (Math.abs(momentum) >= MM_CUTOFF_1_8) {
                    nextOffset += momentum;
                } else {
                    momentum = 0;
                }
                if (offset != 0.0f) {
                    // prevents the first number being added twice
                    newOffsetArray.add(startingHeight + offset);
                }
                continue;
            }
            break;
        }
        return newOffsetArray;
    }

    public String entranceGenerator(float height, boolean exclusive) {
        if (height > 0) {
            if (height % 1 < .0125) {
                return height + " - td ceiling";
            } else if (height % 1 > .1875 && height % 1 < .2) {
                return height + " - td floor";
            } else if (height % 1 > .5 && height % 1 < .5125) {
                return height + " - cake + bean";
            } else if (height % 1 > .5625 && height % 1 < .575) {
                return height + " - bed + piston";
            }
        }
        return exclusive ? "" : String.valueOf(height);
    }

    public float calculateJumpApex(double startingBlipHeight) {
        return (float) (startingBlipHeight + 1.2491871);
    }

    public float getPosition() {
        return position;
    }

    public float getNextPosition() {
        return nextPosition;
    }

    public float getMinimumBottomBlipHeight() {
        return minimumBottomBlipHeight;
    }

    private void setMinimumBottomBlipHeight(float minimumBottomBlipHeight) {
        this.minimumBottomBlipHeight = minimumBottomBlipHeight;
    }

    private void setPositions(float position, float nextPosition) {
        this.position = position;
        this.nextPosition = nextPosition;
    }
}

package tech.lucidsoft.cache.definitions;

public class SequenceDefinition {

    private int id;
    private int[] frameIds;
    private int[] chatFrameIds;
    private int[] frameLengths;
    private int[] frameSounds;
    private int frameStep;
    private int[] interleaves;
    private boolean stretches;
    private int forcedPriority;
    private int leftHandItem;
    private int rightHandItem;
    private int maxLoops;
    private int precedenceAnimating;
    private int priority;
    private int replyMode;

    public SequenceDefinition() {
        setDefaults();
    }

    public SequenceDefinition(int id) {
        setDefaults();
        setId(id);
    }

    public void setDefaults() {
        setFrameStep(-1);
        setStretches(false);
        setForcedPriority(5);
        setLeftHandItem(-1);
        setRightHandItem(-1);
        setMaxLoops(99);
        setPrecedenceAnimating(-1);
        setPriority(-1);
        setReplyMode(2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getFrameIds() {
        return frameIds;
    }

    public void setFrameIds(int[] frameIds) {
        this.frameIds = frameIds;
    }

    public int[] getChatFrameIds() {
        return chatFrameIds;
    }

    public void setChatFrameIds(int[] chatFrameIds) {
        this.chatFrameIds = chatFrameIds;
    }

    public int[] getFrameLengths() {
        return frameLengths;
    }

    public void setFrameLengths(int[] framLengths) {
        this.frameLengths = framLengths;
    }

    public int[] getFrameSounds() {
        return frameSounds;
    }

    public void setFrameSounds(int[] frameSounds) {
        this.frameSounds = frameSounds;
    }

    public int getFrameStep() {
        return frameStep;
    }

    public void setFrameStep(int frameStep) {
        this.frameStep = frameStep;
    }

    public int[] getInterleaves() {
        return interleaves;
    }

    public void setInterleaves(int[] interleaves) {
        this.interleaves = interleaves;
    }

    public boolean isStretches() {
        return stretches;
    }

    public void setStretches(boolean stretches) {
        this.stretches = stretches;
    }

    public int getForcedPriority() {
        return forcedPriority;
    }

    public void setForcedPriority(int forcedPriority) {
        this.forcedPriority = forcedPriority;
    }

    public int getLeftHandItem() {
        return leftHandItem;
    }

    public void setLeftHandItem(int leftHandItem) {
        this.leftHandItem = leftHandItem;
    }

    public int getRightHandItem() {
        return rightHandItem;
    }

    public void setRightHandItem(int rightHandItem) {
        this.rightHandItem = rightHandItem;
    }

    public int getMaxLoops() {
        return maxLoops;
    }

    public void setMaxLoops(int maxLoops) {
        this.maxLoops = maxLoops;
    }

    public int getPrecedenceAnimating() {
        return precedenceAnimating;
    }

    public void setPrecedenceAnimating(int precedenceAnimating) {
        this.precedenceAnimating = precedenceAnimating;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getReplyMode() {
        return replyMode;
    }

    public void setReplyMode(int replyMode) {
        this.replyMode = replyMode;
    }
}

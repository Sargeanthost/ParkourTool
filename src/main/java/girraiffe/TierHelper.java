package girraiffe;

import java.util.*;
import java.util.stream.DoubleStream;

public class TierHelper {
    //! naming conventions
    
    //combined- both offset and height
    //height- the y coord
    //offset - the tier mapping
    //tier - namesake
    //delta - the difference between two things

    //do not do combinations in this class!

    private float offset = 0.0f;
    private float nextOffset = 0.0f;

    private double[] offsetArray;

    public TierHelper() {
        initOffsetArray();
    }

//    /**
//     * Calculates if the blip is possible based off the given parameters. Note: a blip is possible
//     * when the delta between the top and the bottom of the blip is less than the tier difference
//     * between the nearest landing tier and the next tier down.
//     *
//     * @param predictedOffset if you were to blip this would be your blip height
//     * @param blipTopHeight the Y level of th top part of the blip
//     * @param blipBottomHeight the Y level of the bottom part of the blip
//     * @return returns if the blip is possible
//     */
    //TODO
//    public boolean isBlipPossible(
//            float predictedOffset,
//            float blipTopHeight,
//            float blipBottomHeight) {
//        var offsetDelta =
//                Math.abs(
//                        getPreviousOffset(predictedOffset) - predictedOffset
//                );
//        minimumBottomBlipHeight = blipTopHeight - offsetDelta;
//        // any value lower than this will cause the blip to fail
//
//        return blipTopHeight - offsetDelta <= blipBottomHeight;
//        // <= blipBottomHeight;
//    }
//


    //replace calculateBlip in controller
    public float calculateOffsets(float heightDelta){
        var momentum = 0.42f;
        var nextOffset = momentum;
        var offset = 0.0f;

        if (heightDelta < 0 ){
            System.out.println("height delta < 0 pls fix");
            return 0.0f;
        }

        while(heightDelta > Math.abs(nextOffset)){
            offset += momentum;
            momentum -= 0.08f;
            momentum *= 0.98f;
            if (Math.abs(momentum) >= 0.005D){
                nextOffset += momentum;
            } else {
                momentum = 0;
            }
        }
        setOffsets(offset, nextOffset);
        return offset;
        //-5.3446336 for 6.0f delta
    }

    public float getJumpApex(double startingHeight) {
        return (float)(startingHeight + 1.2491871);
    }

    public float getOffset(){
        return offset;
    }

    public float getNextOffset(){
        return nextOffset;
    }

    private void setOffsets(float offset, float nextOffset){
        this.offset = offset;
        this.nextOffset = nextOffset;
    }

    private void initOffsetArray(){
        //easiest way i thought of
        //TODO make these values more precise or generate on the fly
        offsetArray = new double[]{
                0.0f,
                0.4200f,
                0.7532f,
                1.0013f,
                1.1661f,
                1.2492f,
                1.2492f,
                1.1708f,
                1.0156f,
                0.7850f,
                0.4807f,
                0.1041f,
                -0.3434f,
                -0.8604f,
                -1.4454f,
                -2.0971f,
                -2.8142f,
                -3.5953f,
                -4.4392f,
                -5.3446f,
                -6.3104f,
                -7.3352f,
                -8.4179f,
                -9.5573f,
                -10.7524f,
                -12.0020f,
                -13.3050f,
                -14.6603f,
                -16.0669f,
                -17.5238f,
                -19.0299f,
                -20.5843f,
                -22.1861f,
                -23.8341f,
                -25.5277f,
                -27.2657f,
                -29.0474f,
                -30.8719f,
                -32.7383f,
                -34.6457f,
                -36.5934f,
                -38.5806f,
                -40.6064f,
                -42.6701f,
                -44.7709f,
                -46.9081f,
                -49.0810f,
                -51.2888f,
                -53.5308f,
                -55.8064f,
                -58.1149f,
                -60.4556f,
                -62.8279f,
                -65.2312f,
                -67.6648f,
                -70.1281f,
                -72.6205f,
                -75.1416f,
                -77.6905f,
                -80.2670f,
                -82.8702f,
                -85.4998f,
                -88.1553f,
                -90.8360f,
                -93.5415f,
                -96.2713f,
                -99.0249f,
                -101.8018f,
                -104.6016f,
                -107.4237f,
                -110.2679f,
                -113.1336f,
                -116.0203f,
                -118.9277f,
                -121.8554f,
                -124.8029f,
                -127.7698f,
                -130.7559f,
                -133.7606f,
                -136.7836f,
                -139.8245f,
                -142.8831f,
                -145.9588f,
                -149.0515f,
                -152.1606f,
                -155.2861f,
                -158.4273f,
                -161.5842f,
                -164.7564f,
                -167.9434f,
                -171.1452f,
                -174.3613f,
                -177.5915f,
                -180.8355f,
                -184.0930f,
                -187.3638f,
                -190.6475f,
                -193.9440f,
                -197.2529f,
                -200.5741f,
                -203.9072f,
                -207.2521f,
                -210.6085f,
                -213.9761f,
                -217.3548f,
                -220.7443f,
                -224.1445f,
                -227.5550f,
                -230.9757f,
                -234.4064f,
                -237.8469f,
                -241.2970f,
                -244.7565f,
                -248.2252f,
                -251.7029f,
                -255.1895f};
    }

    public DoubleStream getOffsetArrayStream(){
        return Arrays.stream(offsetArray);
    }
}

package com.theta.animationdemo.ParallaxViewPagerDemo;

import android.location.Location;

/**
 * Created by ashish on 20/2/18.
 */

public class Events {

    // Event used to send message from activity to fragment.
    public static class ActivityFragmentMessage {

        private Location message;
        public ActivityFragmentMessage(Location message) {
            this.message = message;
        }
        public Location getMessage() {
            return message;
        }
    }

    // Event used to send message from activity to activity.
    public static class ActivityMessage{
        private Location location;

        public ActivityMessage(Location location){
            this.location = location;
        }
        public Location getMessage() {
            return location;
        }
    }

    public static class FragmentData{
        private String mCurrentFragmentPosition;

        public FragmentData(String mCurrentFragmentPosition) {
            this.mCurrentFragmentPosition = mCurrentFragmentPosition;
        }

        public String getFragmentPosition(){
            return mCurrentFragmentPosition;
        }
    }

    public static class FragmentThreeData{
        private  String side;

        public FragmentThreeData(String side) {
            this.side = side;
        }

        public String getSide(){
            return  side;
        }
    }

    public static class LodedFromButton{
        private boolean isLodedFromButton;

        public LodedFromButton(boolean isLodedFromButton) {
            this.isLodedFromButton = isLodedFromButton;
        }

        public boolean getEvent(){
            return  isLodedFromButton;
        }
    }

    public static class LodedFromButtonFragment2{
        private boolean isLodedFromButtonFragment2;

        public LodedFromButtonFragment2(boolean isLodedFromButtonFragment2) {
            this.isLodedFromButtonFragment2 = isLodedFromButtonFragment2;
        }

        public boolean getEventFragment2(){
            return  isLodedFromButtonFragment2;
        }
    }

    public static class LodedFromButtonFragment1{
        private boolean isLodedFromButtonFragment1;

        public LodedFromButtonFragment1(boolean isLodedFromButtonFragment1) {
            this.isLodedFromButtonFragment1 = isLodedFromButtonFragment1;
        }

        public boolean getEventFragment1(){
            return  isLodedFromButtonFragment1;
        }
    }

    public static class LodedFromButtonFragment4{
        private boolean isLodedFromButtonFragment4;

        public LodedFromButtonFragment4(boolean isLodedFromButtonFragment4) {
            this.isLodedFromButtonFragment4 = isLodedFromButtonFragment4;
        }

        public boolean getEventFragment4(){
            return  isLodedFromButtonFragment4;
        }
    }


}

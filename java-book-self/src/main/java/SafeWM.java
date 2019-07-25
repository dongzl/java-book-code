import java.util.concurrent.atomic.AtomicReference;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-25 16:54
 */
public class SafeWM {

    class WMRange {
        final int upper;

        final int lower;

        public WMRange(int upper, int lower) {
            this.upper = upper;
            this.lower = lower;
        }

        final AtomicReference<WMRange> rf = new AtomicReference<>(new WMRange(0, 0));

        void setUpper(int v) {
            while (true) {
                WMRange or = rf.get();
                if (v < or.lower) {
                    throw new IllegalArgumentException();
                }
                WMRange nr = new WMRange(v, or.lower);
                if (rf.compareAndSet(or, nr)) {
                    return;
                }
            }
        }
    }
}

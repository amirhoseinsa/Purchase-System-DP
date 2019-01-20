import java.util.EventListener;

/**
 *
 * @author amir hosein Created on 19 Jan 2018
 */
public interface OnRawBillReiceved extends EventListener {
    public void callBillMaker(RawCart rawCart);
}

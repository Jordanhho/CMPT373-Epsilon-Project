package store;

import javax.annotation.Nonnull;

public class ShiftId extends DatabaseId implements ShiftIdable {

    public ShiftId(@Nonnull Integer rawId) {
        super(rawId);
    }

    @Nonnull
    @Override
    public ShiftId getShiftId() {
        return this;
    }
}

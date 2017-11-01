package store;

import javax.annotation.Nonnull;

public class TeamId extends DatabaseId implements TeamIdable {
    @Nonnull
    protected Integer rawId;

    public TeamId(Integer rawId) { super(rawId); }

    @Override
    @Nonnull
    public Integer getRawId() { return rawId; }

    @Override
    public TeamId getTeamId() { return this; }
}

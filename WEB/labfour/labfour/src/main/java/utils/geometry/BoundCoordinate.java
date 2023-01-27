package utils.geometry;

import java.util.function.DoubleFunction;

public class BoundCoordinate implements Coordinate {
    final private DoubleFunction<Double> getExpression;
    final private DoubleCoordinate boundValue;

    public BoundCoordinate(DoubleCoordinate boundValue) {
        this.boundValue = boundValue;
        getExpression = value -> value;
    }

    public BoundCoordinate(DoubleCoordinate boundValue, DoubleFunction<Double> getExpression) {
        this.boundValue = boundValue;
        this.getExpression = getExpression;
    }

    @Override
    public double get() {
        return getExpression.apply(boundValue.get());
    }

    @Override
    public void set(double newValue) {
        boundValue.set(newValue);
    }
}

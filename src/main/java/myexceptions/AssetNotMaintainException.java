package myexceptions;

public class AssetNotMaintainException extends RuntimeException {
    public AssetNotMaintainException(String message) {
        super(message);
    }
}
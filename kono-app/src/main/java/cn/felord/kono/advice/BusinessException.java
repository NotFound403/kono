package cn.felord.kono.advice;

/**
 * @author Dax
 * @since 15:23  2019-07-31
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = -5694288820410981620L;

    public BusinessException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

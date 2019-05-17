package net.lantrack.framework.core.lcexception;

public class ServiceException extends DaoException {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        super(msg);
    }


}

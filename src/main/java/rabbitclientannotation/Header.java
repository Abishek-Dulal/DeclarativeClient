package rabbitclientannotation;

/**
 * @author Abishek Dulal on 2021-10-07
 */
public @interface Header {
    String key();
    String value();
}

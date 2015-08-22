package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 10:42
 * Declaration: All Rights Reserved !!!
 */
public interface Handler<M, N> {
    public M hand(N n);
}

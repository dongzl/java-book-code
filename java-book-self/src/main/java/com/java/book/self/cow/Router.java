package com.java.book.self.cow;

import java.util.Objects;

/**
 * @author dongzonglei
 * @description
 * @date 2019-07-29 18:35
 */
public final class Router {

    public final String ip;

    public final Integer port;

    public final String iface;

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Router router = (Router) o;
        return Objects.equals(ip, router.ip) &&
                Objects.equals(port, router.port) &&
                Objects.equals(iface, router.iface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, port, iface);
    }
}

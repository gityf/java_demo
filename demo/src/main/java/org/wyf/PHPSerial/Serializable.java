package org.wyf.PHPSerial;

interface Serializable {
        byte[] serialize();
        void unserialize(byte[] ss);
}
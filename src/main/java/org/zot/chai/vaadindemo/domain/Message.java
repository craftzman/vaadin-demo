package org.zot.chai.vaadindemo.domain;

import java.time.Instant;

public record Message(String userName, String text, Instant time) {
}

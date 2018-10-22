package com.tomek.gamevidence.domain;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

/**
 * Common table record validity definition
 */
@Embeddable
public class Validity {

  @Type(type = "yes_no")
  @Column(name = "VALID", nullable = false)
  protected Boolean valid;

  public Validity(Boolean valid) {
    this.valid = valid;
  }

  public Validity() {
  }

  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }

  public boolean isValid(Instant currentDate) {
    return Boolean.TRUE.equals(getValid());
  }

}

package org.botnicholas.projects.webfluxserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "goods")
public class Good {
  @Id
  @Column("good_id")
  private Long id;
  @Column("good_name")
  private String name;
  @Column("good_description")
  private String description;
  @Column("good_image")
  private String image;
}

package com.ingenuiq.note

import java.time._

import kamon.Kamon
import kamon.trace.Identifier

package object utils {

  val defaultZone: ZoneId = ZoneId.of("UTC")

  def now(zoneId: ZoneId = defaultZone): LocalDateTime = LocalDateTime.now(zoneId)

  def currentSpanId: String = {
    val currentSpan = Kamon.currentSpan()
    val spanID      = currentSpan.id

    if (spanID == Identifier.Empty)
      "undefined"
    else
      spanID.string
  }

  def currentParentSpanId: String = {
    val currentSpan = Kamon.currentSpan()
    val parentId    = currentSpan.parentId

    if (parentId == Identifier.Empty)
      "undefined"
    else
      parentId.string
  }

  def currentTraceId: String = {
    val currentSpan = Kamon.currentSpan()
    val traceID     = currentSpan.trace.id

    if (traceID == Identifier.Empty)
      "undefined"
    else
      traceID.string
  }

  def localDateTimeToLong(date: LocalDateTime): Long          = date.toInstant(ZoneOffset.UTC).toEpochMilli
  def longToLocalDateTime(date: Long):          LocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneOffset.UTC)

  def localDateToLong(date: LocalDate): Long      = localDateTimeToLong(date.atStartOfDay)
  def longToLocalDate(date: Long):      LocalDate = longToLocalDateTime(date).toLocalDate

  def bigDecimalToString(value: BigDecimal): String     = value.toString
  def stringToBigDecimal(value: String):     BigDecimal = BigDecimal(value)

  def coalesce[B, A](kv: Seq[(B, Seq[A])]): Seq[(B, Seq[A])] = {
    val r = kv.foldLeft(Map.empty[B, Seq[A]]) {
      case (acc, (k, v)) =>
        val newV = v ++ acc.getOrElse(k, Seq.empty[A])
        acc ++ Map(k -> newV)
    }
    r.toVector
  }

}

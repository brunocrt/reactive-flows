/*
 * Copyright 2015 Heiko Seeberger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.heikoseeberger.reactiveflows
package proto

import java.time.Instant.now
import org.scalatest.{ Matchers, WordSpec }

final class FlowSerializerSpec extends WordSpec with Matchers {
  import Flow._

  private val serializer = new FlowSerializer

  "FlowSerializer" should {
    "serialize and deserialize GetPosts" in {
      val o                  = GetPosts(0, 1)
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }

    "serialize and deserialize Posts" in {
      val o                  = Posts(Vector(Post(0, "text", now())))
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }

    "serialize and deserialize AddPost" in {
      val o                  = AddPost("text")
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }

    "serialize and deserialize PostAdded" in {
      val o                  = PostAdded("name", Post(0, "text", now()))
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }

    "serialize and deserialize Envelope with GetPosts" in {
      val o                  = CommandEnvelope("name", GetPosts(0, 1))
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }

    "serialize and deserialize Envelope with AddPost" in {
      val o                  = CommandEnvelope("name", AddPost("text"))
      val (manifest, binary) = serialize(o)
      serializer.fromBinary(binary, manifest) shouldBe o
    }
  }

  private def serialize(o: AnyRef) = (serializer.manifest(o), serializer.toBinary(o))
}

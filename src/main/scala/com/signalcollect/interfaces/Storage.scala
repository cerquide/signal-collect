/*
 *  @author Daniel Strebel
 *
 *  Copyright 2011 University of Zurich
 *      
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  
 *         http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.signalcollect.interfaces

import com.signalcollect._
import scala.collection.mutable.IndexedSeq
import scala.collection.mutable.Iterable
import java.util.Set

/**
 * High level interface to abstract all vertex storage related implementations
 */
abstract class Storage {
  def vertices: VertexStore
  def toSignal: Set[Vertex[_, _]] //collection of all vertices that need to signal
  def toCollect: Set[Vertex[_, _]] //collection of all vertices that need to collect
  def cleanUp
  def serializer: Serializer
}

/**
 * Stores vertices and makes them retrievable through their associated id.
 */
trait VertexStore {
  def get(id: Any): Vertex[_, _]
  def getAll(condition: Vertex[_, _] => Boolean): List[Vertex[_, _]]
  def put(vertex: Vertex[_, _]): Boolean
  def remove(id: Any)
  def size: Long
  def foreach[U](f: Vertex[_, _] => U)
  def cleanUp
}

/**
 * Defines all functionality needed for serialization/deserialization
 */
trait Serializer {
  def write[A](inputObject: A): Array[Byte]
  def read[A](buffer: Array[Byte]): A
}

package com.ash.kotlinsearch.repository

import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

internal class CommonMongoDBContainer : MongoDBContainer(DockerImageName.parse("mongo:4.0.10")) {

}
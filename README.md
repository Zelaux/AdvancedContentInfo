AdvancedContentInfo
========

`AdvancedContentInfo` is a Mindustry java mod library for making out stats and stats categories.

Source file generation can be useful when doing things such as annotation processing or interacting
with metadata files (e.g., database schemas, protocol formats). By generating code, you eliminate
the need to write boilerplate while also keeping a single source of truth for the metadata.


### Example
You can initialize your stats and your categories in the setStats method of Block:
YourBlock.java
```java
package com.examplemod.blocks;

import acontent.world.meta.AStat;
import acontent.world.meta.AStats;
import mindustry.world.Block;

public class YourBlock extends Block {
    public AStats aStats=new AStats();
    public YourBlock(String name) {
        super(name);
        stats=aStats.copy(stats);
    }

    @Override
    public void setStats() {
        super.setStats();
        aStats.add(AStat.get("your_stat","your_category"),/*your statValue*/);
        aStats.add(AStat.get("your_stat1", AStatCat.get("your_category2")),/*your statValue*/);
        aStats.add(AStat.get("your_stat2", StatCat.function),/*your statValue*/);
    }
}
```
Or make a separate class for this:
YourStats.java
```java
package com.examplemod.blocks;

import acontent.world.meta.AStat;
import acontent.world.meta.AStatCat;
import mindustry.world.meta.StatCat;

public class YourStats {
    public static final AStatCat yourCategory2=AStatCat.get("your_category2");
    public static final AStat yourStat=AStat.get("your_stat","your_category");
    public static final AStat yourStat2=AStat.get("your_stat1", AStatCat.get("your_category2"));
    public static final AStat yourStat3=AStat.get("your_stat2", StatCat.function);
}
```
YourBlock.java
```java
package com.examplemod.blocks;

import acontent.world.meta.AStats;
import mindustry.world.Block;

public class YourBlock extends Block {
    public AStats aStats=new AStats();
    public YourBlock(String name) {
        super(name);
        stats=aStats.copy(stats);
    }

    @Override
    public void setStats() {
        super.setStats();
        aStats.add(YourStats.yourStat,/*your statValue*/);
        aStats.add(YourStats.yourStat2,/*your statValue*/);
        aStats.add(YourStats.yourStat3,/*your statValue*/);
    }
}
```

## Mindustry Mod By Zelaux

## Resources
- [Last Releases](https://github.com/Zelaux/AdvancedContentInfo/releases)

## Authors
- Zelaux(main-programmer)


# Installation Guide
## 1.Via .jar File
* 1.Go to [releases](https://github.com/Zelaux/AdvancedContentInfo/releases).

* 2.Download the latest .jar file.

* 3.Launch Mindustry.

* 4.Open "Mods".

* 5."Import Mod".

* 6."Impot File"

* 7.Find file with name "AdvancedContentInfo.jar" and click "load".

* 8.Play!

## 2.Via Mod Browser
* 1.Go to in-game Mod Browser.

* 2.Find "AdvancedContentInfo" in mod list.

* 3.Download.  

# Build Guide

## PC

* 1.Download intelijIDEA.

* 2.Clone this repository.

* 3.When importing is end, go to Intelij console and type:

Windows      |  MacOSX       | Linux
------------ | ------------- | -------------
gradlew jar  | ./gradlew jar | ./gradlew jar

* 4.When compilation is end, your build will be in "build/libs"
Download
--------

Depend via Maven:
```xml
<dependency>
	    <groupId>com.github.Zelaux</groupId>
	    <artifactId>AdvancedContentInfo</artifactId>
	    <version>v1</version>
</dependency>
```
or Gradle:
```groovy
dependencies {
        implementation 'com.github.Zelaux:AdvancedContentInfo:v1'
}
```

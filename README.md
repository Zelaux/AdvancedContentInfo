AdvancedContentInfo
========
`AdvancedContentInfo` is a Mindustry java mod library for making out stats and stats categories.

<img width="960" alt="banner1" src="https://user-images.githubusercontent.com/58040045/128066861-741ce790-6048-44bf-9922-2489e35f21bb.png">


### Usage/Examples
translations are written as for standard categories / stat / units:
 * ```AStatCat``` - category.lowercaseName
 * ```AStat``` - stat.lowercaseName
 * ```AStatUnit``` - category.lowercaseName

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
        aStats.add(Stat.health,/*your statValue*/);
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
        aStats.add(Stat.health,/*your statValue*/);
    }
}
```
You can use index at the end of each .get method to change the position in the stat list or stat category.
Stat category without index:
```java
public class GasStats {
    public static AStat gasCapacity = AStat.get("gasCapacity", AStatCat.get("gasses"));
}
```
![image](https://user-images.githubusercontent.com/58040045/127939116-af61d188-019b-4c08-a782-b478c02fe8e5.png)

Stat category with index:
```java
public class GasStats {
    public static AStat gasCapacity = AStat.get("gasCapacity", AStatCat.get("gasses", StatCat.liquids.ordinal()+1));
}
```
![image](https://user-images.githubusercontent.com/58040045/127939214-da6bb475-14d6-4a36-a6bf-1335effe659d.png)

Stat without index:
```java
public class BDStat {
    public static final AStat rotorsCount=AStat.get("rotorsCount", StatCat.general);
}
```
![image](https://user-images.githubusercontent.com/58040045/128065351-ccda517b-baf8-461b-8974-60305c32faee.png)

Stat with index:
```java
public class BDStat {
    public static final AStat rotorsCount=AStat.get("rotorsCount", StatCat.general, Stat.size.ordinal()+1);
}
```
![image](https://user-images.githubusercontent.com/58040045/128065599-8ff994b2-f9ea-4377-9e59-1392c338b698.png)

Also you can made custom StatUnits
Example:
```java
public void setStats(){
	aStats.add(GasStats.gasCapacity, gasCapacity, AStatUnit.get("gasUnits"));
}
```
Result:
![image](https://user-images.githubusercontent.com/58040045/129361681-b46517f8-bc91-4cd3-a298-7a6845fb9482.png)


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
	    <version>v1.3</version>
</dependency>
```
or Gradle:
```groovy
dependencies {
        implementation 'com.github.Zelaux:AdvancedContentInfo:v1.3'
}
```

And don't forget to add the dependency to your mod.(h)json
```hjson
dependencies: ["advanced-content-info"]
```

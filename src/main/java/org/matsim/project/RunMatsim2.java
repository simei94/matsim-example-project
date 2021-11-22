package org.matsim.project;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkFactory;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.utils.geometry.CoordUtils;

public class RunMatsim2 {

    public static void main(String args[]) {

        Config config = ConfigUtils.createConfig();
        config.controler().setOverwriteFileSetting(OutputDirectoryHierarchy.OverwriteFileSetting.deleteDirectoryIfExists);
        config.controler().setLastIteration(2);

        Scenario scenario = ScenarioUtils.createScenario(config);
        NetworkFactory factory = scenario.getNetwork().getFactory();

        Node n0 = factory.createNode(Id.createNodeId(0),CoordUtils.createCoord(0.,0.));
        Node n1 = factory.createNode(Id.createNodeId(1),CoordUtils.createCoord(2.,2.));

        Link l0 = factory.createLink(Id.createLinkId(0_1),n0,n1);


        scenario.getNetwork().addNode(n0);
        scenario.getNetwork().addNode(n1);
        scenario.getNetwork().addLink(l0);

        Controler controler = new Controler(scenario);
        controler.run();

    }

}

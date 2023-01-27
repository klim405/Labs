import React from "react";
import CoordinatesNet from "./CoordinatesNet";
import PointsLayer from "./PointsLayer";
import GraphLayer from "./GraphLayer";

export default class Graph extends React.Component {
    constructor(props) {
        super(props);
        this.sendData = this.sendData.bind(this)
    }
    // todo click

    render() {
        return (
            <div className="graph">
                <svg id="plot" viewBox="0 0 1200 1200" onClick={this.sendData} onMouseMove={this.moveData}>
                    <CoordinatesNet/>
                    {this.props.selectedRadius.map((radius) => (
                        <GraphLayer radius={radius} key={radius.toString()}/>
                    ))}
                    <PointsLayer hitInfos={this.props.hitInfos} selectedRadius={this.props.selectedRadius}/>
                </svg>
            </div>
        );
    }

    sendData(evt) {
        let target = document.getElementById('plot').getBoundingClientRect();
        let x = (evt.clientX - target.left - target.width/2) / (target.width / 12),
            y = -(evt.clientY - target.top - target.width/2) / (target.width / 12);
        this.props.addAction({
            coordsX: [x.toFixed(5)],
            coordY: y.toFixed(5),
            radius: this.props.selectedRadius
        })
    }
}
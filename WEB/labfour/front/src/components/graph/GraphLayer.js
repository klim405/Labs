import React from "react";

export default class GraphLayer extends React.Component {
    ZERO_X = 600;
    ZERO_Y = 600;

    constructor(props) {
        super(props);
        this.isReversed = props.radius < 0;
        this.radius = Math.round(props.radius * 100);
        this.halfRadius = Math.round(this.radius/2);
    }

    getClipPathId() {
        return `cut-off-circle-${this.isReversed ? 'r' : ''}${this.radius}`;
    }

    getClipPathUrl() {
        return `url(#${this.getClipPathId()})`;
    }

    getTrianglePoints() {
        return `600,600 600,${this.ZERO_Y + this.radius} ${this.ZERO_X + this.halfRadius},600`;
    }

    getRectPoints() {
        return `600,600 600,${this.ZERO_Y + this.radius} ${this.ZERO_X - this.halfRadius},${this.ZERO_Y + this.radius} ${this.ZERO_X - this.halfRadius},600`;
    }

    getClassName() {
        return `figure ${this.isReversed ? 'reversed' : 'base'}`
    }

    render() {
        return (
            <g>
                <clipPath id={this.getClipPathId()}>
                    <rect x={this.isReversed ? '50%' : '0'} y={this.isReversed ? '50%' : '0'} width="50%" height="50%"/>
                </clipPath>
                <circle className={this.getClassName()} cx="50%" cy="50%" r={Math.abs(this.halfRadius)} clipPath={this.getClipPathUrl()}/>
                <polygon className={this.getClassName()} points={this.getTrianglePoints()}/>
                <polygon className={this.getClassName()} points={this.getRectPoints()}/>
            </g>
        )
    }
}
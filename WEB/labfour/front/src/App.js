import React from 'react';
import {HashRouter, Routes, Route} from 'react-router-dom';
import {connect} from "react-redux";
import {hitInfosFetchData, hitInfosAddData, hitInfosClear} from "./actions/hitInfos";
import ShooterPage from "./pages/ShooterPage";
import LoginPage from "./pages/LoginPage";
import {getUsername} from "./utils/auth";
import LogoutPage from "./pages/LogoutPage";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.addHitInfo = this.addHitInfo.bind(this)
    this.updateProps = this.updateProps.bind(this)
  }

  render() {
    return (
        <HashRouter>
          <div>
            <header>
              Климович Вадим, P32091,<br/>№111049
            </header>
            <Routes>
              <Route path="/" element={
                <ShooterPage hitInfos={this.props.hitInfos} addHit={this.addHitInfo}/>
              }/>
              <Route path="/login" element={
                <LoginPage/>
              }/>
              <Route path="/logout" element={
                <LogoutPage clearHit={() => this.props.clearData()}/>
              }/>
            </Routes>
          </div>
        </HashRouter>
    );
  }

  addHitInfo(info) {
    info.radius.forEach((r) => {
      info.coordsX.forEach((x) => {
        this.props.addData("/labfour/api/addhit",
            `/labfour/api/hits/${getUsername()}`,
            getUsername(),
            x, info.coordY, r
            )
      })})
  }

  componentDidMount() {
    this.props.fetchData(`/labfour/api/hits/${getUsername()}`)
    this.updateProps()
  }

  updateProps() {
    this.props.fetchData(`/labfour/api/hits/${getUsername()}`)
    setTimeout(() => {this.updateProps()}, 5000)
  }
}

const mapStateToProps = (state) => {
  return {
    hitInfos: state.hitInfos
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    clearData: () => {dispatch(hitInfosClear())},
    fetchData: url => {dispatch(hitInfosFetchData(url))},
    addData: (addUrl, urlList, user, coordX, coordY, radius) => {dispatch(hitInfosAddData(addUrl, urlList, user, coordX, coordY, radius))}
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(App);

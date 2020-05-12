import React, {Component} from "react";
import Navbar from "../Navbar/Navbar";
import {Link} from "react-router-dom";
import { connect } from "react-redux";
import {getLeagues} from "../../actions/leagueActions";
import PropTypes from "prop-types";
import LeagueItem from "./LeagueItem";

class Leagues extends Component {

    componentDidMount() {
        this.props.getLeagues();
    }

    render() {
        const {leagues} = this.props.league;

        return (<div>
            <Navbar />

            <div className="container">
                <React.Fragment>
                    <div className="row">
                        <Link to={"/createLeague"} className="btn btn-outline-warning mx-auto my-5">Create League</Link>
                    </div>
                </React.Fragment>

                <table className="table table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col">League Name</th>
                        <th scope="col">Sport</th>
                        <th scope="col">#</th>
                    </tr>
                    </thead>
                    <tbody>
                    {leagues.map(league =>(
                        <LeagueItem key={league.id} league={league}/>
                    ))
                    }
                    </tbody>
                </table>

            </div>

        </div>);
    }
}

Leagues.propTypes = {
    league: PropTypes.object.isRequired,
    getLeagues: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    league:state.league,
});

export default connect(mapStateToProps,{getLeagues}) (Leagues);
import React, {Component} from "react";
import Navbar from "../Navbar/Navbar";
import {Link} from "react-router-dom";
import { connect } from "react-redux";
import {getSports} from "../../actions/sportActions";
import PropTypes from "prop-types";
import SportItem from "./SportItem";

class Sports extends Component {

    componentDidMount() {
        this.props.getSports();
    }

    render() {
        const {sports} = this.props.sport;

        return (<div>
            <Navbar />

            <div className="container">
                <React.Fragment>
                    <div className="row">
                        <Link to={"/createSports"} className="btn btn-outline-warning mx-auto my-5">Create Sport</Link>
                    </div>
                </React.Fragment>

                <table className="table table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col">Sport</th>
                        <th scope="col">#</th>
                    </tr>
                    </thead>
                    <tbody>
                    {sports.map(sport =>(
                        <SportItem key={sport.id} sport={sport}/>
                    ))
                    }
                    </tbody>
                </table>

            </div>

        </div>);
    }
}

Sports.propTypes = {
    sport: PropTypes.object.isRequired,
    getSports: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
    sport:state.sport,
});

export default connect(mapStateToProps,{getSports}) (Sports);
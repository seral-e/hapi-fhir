package ca.uhn.fhir.cr.r4.measure;

import ca.uhn.fhir.cr.r4.IDataRequirementsServiceFactory;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.annotation.OperationParam;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.provider.ProviderConstants;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Library;
import org.hl7.fhir.r4.model.Measure;
import org.springframework.beans.factory.annotation.Autowired;

public class DataRequirementsOperationProvider {
	@Autowired
	IDataRequirementsServiceFactory myR4DataRequirementsServiceFactory;
	/**
	 * Implements the <a href=
	 * "https://www.hl7.org/fhir/R4/measure-operation-data-requirements.html">$evaluate-measure</a>
	 * operation found in the
	 * <a href="http://www.hl7.org/fhir/clinicalreasoning-module.html">FHIR Clinical
	 * Reasoning Module</a>. This implementation aims to be compatible with the CQF
	 * IG.
	 *
	 * @param theId             the id of the Measure to evaluate
	 * @param thePeriodStart    The start of the reporting period
	 * @param thePeriodEnd      The end of the reporting period
	 * @param theRequestDetails The details (such as tenant) of this request. Usually
	 *                          autopopulated HAPI.
	 * @return the calculated Library dataRequirements
	 */
	@Operation(name = ProviderConstants.CR_OPERATION_DATAREQUIREMENTS, idempotent = true, type = Measure.class)
	public Library dataRequirements(
			@IdParam IdType theId,
			@OperationParam(name = "periodStart") String thePeriodStart,
			@OperationParam(name = "periodEnd") String thePeriodEnd,
			RequestDetails theRequestDetails) {
		return myR4DataRequirementsServiceFactory
				.create(theRequestDetails)
				.dataRequirements(theId, thePeriodStart, thePeriodEnd);
	}
}

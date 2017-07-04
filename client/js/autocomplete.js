var kwInput = $('#keywords-input');
var topicsButton = $('#topics button');

function initAutocomplete(granularity) {
	var engine = new Bloodhound({
		datumTokenizer: Bloodhound.tokenizers.obj.whitespace('name'),
		queryTokenizer: Bloodhound.tokenizers.whitespace,
		prefetch: {
			url: 'http://aquasmart.uninova.pt/training/js/keywords-' + granularity + '.json',
			filter: function(list) {
				return $.map(list, function(keyword) {
					return { name: keyword }; });
			}
		}
	});

	kwInput.tagsinput('destroy');
	kwInput.tagsinput({
		typeaheadjs: {
			source: engine,
			displayKey: 'name',
			valueKey: 'name',
		}
	});
	kwInput.tagsinput('removeAll');
	topicsButton.prop('disabled', true);

	// HACK: overrule hardcoded display inline-block of typeahead.js
	$('.twitter-typeahead').css('display', 'inline');
}

/*
 * HACK: we have to initialize twice. Single init does not work.
 * Probably due to buggy behaviour of destroy when applied to an empty object.
 *
*/
initAutocomplete($('input[name=granularity]:checked', '.switch-toggle').val());
initAutocomplete($('input[name=granularity]:checked', '.switch-toggle').val());


kwInput.on('itemAdded', function(event) {
	topicsButton.prop('disabled', false);
});

kwInput.on('itemRemoved', function(event) {
	if(kwInput.tagsinput('items').length == 0)
		topicsButton.prop('disabled', true);
});

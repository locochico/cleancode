package fitness.example.function;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class FitnessExample {

	public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
		return new TestableHtmlBuilder(pageData, includeSuiteSetup).invoke();
	}

	private class TestableHtmlBuilder {
		private PageData pageData;
		private boolean includeSuiteSetup;
		private WikiPage wikiPage;
		private StringBuffer buffer;

		public TestableHtmlBuilder(PageData pageData, boolean includeSuiteSetup) {
			this.pageData = pageData;
			this.includeSuiteSetup = includeSuiteSetup;
			wikiPage = pageData.getWikiPage();
			buffer = new StringBuffer();
		}

		public String invoke() throws Exception {
			if (isTestPages()) {
				includeSetups();
				buffer.append(pageData.getContent());
				includeTeardowns();
			}

			pageData.setContent(buffer.toString());
			return pageData.getHtml();
		}

		private void includeTeardowns() throws Exception {
			includeInherited("TearDown", "teardown");
			if (includeSuiteSetup)
				includeInherited(SuiteResponder.SUITE_TEARDOWN_NAME, "teardown");
		}

		private void includeSetups() throws Exception {
			if (includeSuiteSetup)
				includeInherited(SuiteResponder.SUITE_SETUP_NAME, "setup");
			includeInherited("SetUp", "setup");
		}

		private boolean isTestPages() throws Exception {
			return pageData.hasAttribute("Test");
		}

		private void includeInherited(String suiteSetupName, String mode) throws Exception {
			WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(suiteSetupName, wikiPage);
			if (suiteSetup != null) {
				includePage(suiteSetup, mode);
			}
		}

		private void includePage(WikiPage suiteSetup, String mode) throws Exception {
			WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(suiteSetup);
			String pagePathName = PathParser.render(pagePath);
			buffer.append("!include -" + mode + " .").append(pagePathName).append("\n");
		}
	}
}
